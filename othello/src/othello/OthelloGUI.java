package othello;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class OthelloGUI extends JFrame {

    static final int SIZE = 8;            // 盤面のサイズ（8x8）
    static final int CELL_SIZE = 60;      // マス目の1辺のピクセル
    char[][] board = new char[SIZE][SIZE]; // 盤面を表す配列 '.'空白 'B'黒 'W'白
    char currentPlayer = 'B';             // 現在のプレイヤー ('B'=黒=人間, 'W'=白=CPU)
    boolean gameOver = false;             // ゲーム終了フラグ
    Random random = new Random();         // CPUのランダム選択用

    // コンストラクタ：ウィンドウの初期設定、盤面初期化、マウスクリック処理
    public OthelloGUI() {
        setTitle("オセロ - 強化CPU");
        setSize(SIZE * CELL_SIZE + 20, SIZE * CELL_SIZE + 40);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        initializeBoard(); // 初期配置の石をセット

        BoardPanel panel = new BoardPanel();

        // マウスクリックで石を置く処理
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (gameOver || currentPlayer != 'B') return; // ゲーム終了かCPUターンなら何もしない

                int col = e.getX() / CELL_SIZE; // クリック位置の列
                int row = e.getY() / CELL_SIZE; // クリック位置の行

                if (isValidMove(row, col, 'B')) { // 合法手か確認
                    placeStone(row, col, 'B');   // 石を置く
                    currentPlayer = 'W';
                    panel.repaint();

                    // CPUターン
                    SwingUtilities.invokeLater(() -> {
                        cpuMove();   // CPUが最適手を置く
                        panel.repaint();
                    });
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    // 盤面初期化：中央に初期の4つの石を配置
    void initializeBoard() {
        for (int i = 0; i < SIZE; i++)
            Arrays.fill(board[i], '.'); // 空白で初期化

        board[3][3] = 'W';
        board[3][4] = 'B';
        board[4][3] = 'B';
        board[4][4] = 'W';
    }

    // 指定位置が合法手か判定
    boolean isValidMove(int row, int col, char player) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) return false;
        if (board[row][col] != '.') return false; // 空いていないマスは不可

        char opponent = (player == 'B') ? 'W' : 'B';
        int[] dx = {-1,-1,-1,0,0,1,1,1};
        int[] dy = {-1,0,1,-1,1,-1,0,1};

        for (int dir=0; dir<8; dir++) { // 8方向チェック
            int x=row+dx[dir], y=col+dy[dir];
            boolean foundOpponent=false;
            while(x>=0 && x<SIZE && y>=0 && y<SIZE && board[x][y]==opponent){
                x+=dx[dir]; y+=dy[dir]; foundOpponent=true; // 相手の石を挟めるか
            }
            if(foundOpponent && x>=0 && x<SIZE && y>=0 && y<SIZE && board[x][y]==player) return true;
        }
        return false;
    }

    // 石を置く処理（盤面更新＆石の反転＆ゲーム終了判定）
    void placeStone(int row, int col, char player) {
        board[row][col] = player;       // 石を置く
        flipStones(row, col, player);   // 挟んだ石を反転
        checkGameOver();                // ゲーム終了チェック
    }

    // 石を反転させる処理
    void flipStones(int row, int col, char player) {
        char opponent = (player == 'B') ? 'W' : 'B';
        int[] dx = {-1,-1,-1,0,0,1,1,1};
        int[] dy = {-1,0,1,-1,1,-1,0,1};

        for(int dir=0; dir<8; dir++){
            int x=row+dx[dir], y=col+dy[dir];
            java.util.List<Point> toFlip = new ArrayList<>();
            while(x>=0 && x<SIZE && y>=0 && y<SIZE && board[x][y]==opponent){
                toFlip.add(new Point(x,y)); // 反転対象に追加
                x+=dx[dir]; y+=dy[dir];
            }
            if(!toFlip.isEmpty() && x>=0 && x<SIZE && y>=0 && y<SIZE && board[x][y]==player){
                for(Point p: toFlip) board[p.x][p.y]=player; // 反転
            }
        }
    }

    // 指定位置でひっくり返せる石の枚数を数える
    int countFlips(int row, int col, char player){
        int total=0;
        char opponent = (player == 'B') ? 'W' : 'B';
        int[] dx = {-1,-1,-1,0,0,1,1,1};
        int[] dy = {-1,0,1,-1,1,-1,0,1};
        for(int dir=0; dir<8; dir++){
            int x=row+dx[dir], y=col+dy[dir], cnt=0;
            while(x>=0 && x<SIZE && y>=0 && y<SIZE && board[x][y]==opponent){
                cnt++; x+=dx[dir]; y+=dy[dir];
            }
            if(cnt>0 && x>=0 && x<SIZE && y>=0 && y<SIZE && board[x][y]==player) total+=cnt;
        }
        return total;
    }

    // CPUの評価関数（角優先・辺優先・石枚数評価）
    int evaluateMove(int row, int col, char player){
        // 角は最優先
        if((row==0 && col==0)||(row==0 && col==SIZE-1)||(row==SIZE-1 && col==0)||(row==SIZE-1 && col==SIZE-1))
            return 1000;
        // 角隣接は低スコア（リスク回避）
        if((row<=1 && col<=1)||(row<=1 && col>=SIZE-2)||(row>=SIZE-2 && col<=1)||(row>=SIZE-2 && col>=SIZE-2))
            return 1;
        // 辺は中スコア
        if(row==0||row==SIZE-1||col==0||col==SIZE-1) return 10 + countFlips(row,col,player);
        // 内側マスは反転数で評価
        return countFlips(row,col,player);
    }

    // CPUのターン：最適手を選んで置く
    void cpuMove(){
        java.util.List<Point> bestMoves=new ArrayList<>();
        int maxScore=-1;
        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                if(isValidMove(i,j,'W')){
                    int score=evaluateMove(i,j,'W');
                    if(score>maxScore){ bestMoves.clear(); bestMoves.add(new Point(i,j)); maxScore=score; }
                    else if(score==maxScore) bestMoves.add(new Point(i,j));
                }
            }
        }
        if(!bestMoves.isEmpty()){
            Point move = bestMoves.get(random.nextInt(bestMoves.size())); // 同点ならランダム
            placeStone(move.x, move.y, 'W');
        }
        currentPlayer='B';
    }

    // プレイヤーが置ける合法手があるかチェック
    boolean hasValidMove(char player){
        for(int i=0;i<SIZE;i++) for(int j=0;j<SIZE;j++)
            if(isValidMove(i,j,player)) return true;
        return false;
    }

    // ゲーム終了判定：両者置ける手が無ければ終了
    void checkGameOver(){
        if(!hasValidMove('B') && !hasValidMove('W')){
            gameOver=true;
            int black=0, white=0;
            for(int i=0;i<SIZE;i++)
                for(int j=0;j<SIZE;j++){
                    if(board[i][j]=='B') black++;
                    else if(board[i][j]=='W') white++;
                }
            String result;
            if(black>white) result="黒の勝ち！ "+black+"-"+white;
            else if(white>black) result="白の勝ち！ "+white+"-"+black;
            else result="引き分け！ "+black+"-"+white;
            JOptionPane.showMessageDialog(this,result,"ゲーム終了",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // 盤面描画用パネル
    class BoardPanel extends JPanel{
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setColor(new Color(0,128,0)); // 背景緑
            g.fillRect(0,0,getWidth(),getHeight());
            g.setColor(Color.BLACK);
            // マス目を描画
            for(int i=0;i<=SIZE;i++){
                g.drawLine(i*CELL_SIZE,0,i*CELL_SIZE,SIZE*CELL_SIZE);
                g.drawLine(0,i*CELL_SIZE,SIZE*CELL_SIZE,i*CELL_SIZE);
            }
            // 石を描画
            for(int i=0;i<SIZE;i++){
                for(int j=0;j<SIZE;j++){
                    if(board[i][j]=='B'){
                        g.setColor(Color.BLACK);
                        g.fillOval(j*CELL_SIZE+5,i*CELL_SIZE+5,CELL_SIZE-10,CELL_SIZE-10);
                    }else if(board[i][j]=='W'){
                        g.setColor(Color.WHITE);
                        g.fillOval(j*CELL_SIZE+5,i*CELL_SIZE+5,CELL_SIZE-10,CELL_SIZE-10);
                        g.setColor(Color.BLACK);
                        g.drawOval(j*CELL_SIZE+5,i*CELL_SIZE+5,CELL_SIZE-10,CELL_SIZE-10);
                    }
                }
            }
            // プレイヤーの合法手をハイライト
            if(!gameOver && currentPlayer=='B'){
                g.setColor(new Color(0,255,0,100));
                for(int i=0;i<SIZE;i++)
                    for(int j=0;j<SIZE;j++)
                        if(isValidMove(i,j,'B'))
                            g.fillOval(j*CELL_SIZE+20,i*CELL_SIZE+20,CELL_SIZE-40,CELL_SIZE-40);
            }
        }
    }

    // メイン関数
    public static void main(String[] args){
        SwingUtilities.invokeLater(OthelloGUI::new);
    }
}