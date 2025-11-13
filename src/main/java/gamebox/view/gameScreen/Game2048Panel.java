package gamebox.view.gameScreen;

import gamebox.game2048.Tile;
import gamebox.game2048.service.entity.Board;

import javax.swing.*;
import java.awt.*;

public class Game2048Panel extends JPanel {
    private static final int GRID_GAP = 3;
    private static final int GRID_SIZE = 4;
    TilePanel[][] tile = new TilePanel[GRID_SIZE][GRID_SIZE];

    Board board = new Board(GRID_SIZE, GRID_SIZE);

    public Game2048Panel() {
        setupPanel();
    }

    private void setupPanel() {
        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE, GRID_GAP, GRID_GAP)); // 5픽셀 간격
        setBackground(Color.white);
        setTiles();
    }

    private void setTiles() {
        for (int r=0; r<GRID_SIZE; r++) {
            for (int c=0; c<GRID_SIZE; c++) {
                TilePanel currentTile = tile[r][c];
                currentTile = new TilePanel();

                /**
                 * 2048 컨트롤러 구현 완료 후 수정
                 */
                Tile tile = board.get(r, c);
                if (tile.getNumber() != 0) {
                    currentTile.setTile(tile.getNumber());
                    currentTile.setColor(tile.getBackgroundColor());
                }

                add(currentTile);
            }
        }
    }

    private void initBoard() {
        // TODO: Board(Tile[][]) 초기화
    }

    public void updateBoard() {
        // TODO: 전달받은 Board 상태를 패널 내부 변수에 저장
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // TODO: 내부 Board 상태를 읽어 타일을 화면에 그리기
    }
}
