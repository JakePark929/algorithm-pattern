package com.jake.private_test.gpttest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class BounceTest extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
    private int ballX = 50; // 공의 초기 X 위치
    private int ballY = 50; // 공의 초기 Y 위치
    private int ballDiameter = 30; // 공의 크기
    private double deltaX = 0; // X 방향 속도
    private double deltaY = 0; // Y 방향 속도
    private double gravity = 1; // 중력 가속도

    private double energyLoss = 0.8; // 바운스 시 에너지 손실 (0~1 사이 값)
    private double groundFriction = 0.95; // 바닥에서의 마찰력

    private Timer timer;
    private JButton startButton, resetButton, pauseButton;
    private JLabel statusLabel;
    private JScrollPane scrollPane;
    private boolean isPaused = false; // 일시 정지 상태를 나타내는 플래그

    private boolean dragging = false; // 드래그 상태를 나타내는 플래그
    private Point dragStartPoint; // 드래그 시작 위치

    public BounceTest(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
        timer = new Timer(20, this); // 20밀리초마다 actionPerformed 호출

        // 시작 버튼 생성 및 이벤트 리스너 추가
        startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            if (isPaused) {
                timer.start(); // 일시 정지 상태에서 다시 시작
                isPaused = false; // 일시 정지 플래그 초기화
            }
        });

        // 일시 정지 버튼 생성 및 이벤트 리스너 추가
        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(e -> {
            if (!isPaused) {
                timer.stop(); // 타이머 멈춤
                isPaused = true; // 일시 정지 플래그 설정
            }
        });

        // 리셋 버튼 생성 및 이벤트 리스너 추가
        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            timer.stop(); // 타이머 멈춤
            resetBall(); // 공의 위치와 속도를 초기화
            repaint(); // 화면 갱신
        });

        // 속도 정보를 표시할 JLabel 생성
        statusLabel = new JLabel("ΔX: " + deltaX + " ΔY: " + deltaY + " gravity: " + gravity);

        // 패널의 레이아웃을 null 로 설정하고 버튼 배치
        setLayout(null);
        startButton.setBounds(10, 10, 80, 30);
        pauseButton.setBounds(100, 10, 80, 30);
        resetButton.setBounds(190, 10, 80, 30);
        statusLabel.setBounds(280, 10, 200, 30);

        add(startButton);
        add(pauseButton);
        add(resetButton);
        add(statusLabel);

        // 마우스 이벤트 리스너 등록
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    // 공의 위치와 속도를 초기화하는 메서드
    private void resetBall() {
        ballX = 50; // 초기 X 위치로 리셋
        ballY = 50; // 초기 Y 위치로 리셋
        deltaX = 0; // 초기 X 속도로 리셋
        deltaY = 0; // 초기 Y 속도로 리셋
        gravity = 1; // 중력 재설정
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED); // 공의 색상
        g.fillOval(ballX, ballY, ballDiameter, ballDiameter); // 공 그리기
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 공이 멈추었는지 확인
        if (deltaX != 0 || deltaY != 0) {
            // 중력 적용 (공이 떨어짐)
            deltaY += gravity;
            ballX += deltaX;
            ballY += deltaY;

            // 공이 바닥에 닿았을 때
            if (ballY + ballDiameter >= getHeight()) {
                ballY = getHeight() - ballDiameter; // 바닥 위치로 고정
                deltaY = -deltaY * energyLoss; // 반사 속도, 에너지 손실 반영

                // 공이 튀는 에너지가 거의 남지 않으면 굴러가도록 전환
                if (Math.abs(deltaY) < 0.5) {
                    deltaY = 0;
                    gravity = 0; // 중력 제거

                    // 마찰력을 적용하여 속도 감소
                    deltaX *= groundFriction;

                    // 속도가 충분히 작아지면 멈추기
                    if (Math.abs(deltaX) < 0.1) { // 임계값을 낮추어 속도가 더 빨리 줄어들게 함
                        deltaX = 0;
                        timer.stop(); // 애니메이션 멈춤
                    }
                }
            }

            // 공이 상단 벽에 닿았을 때
            if (ballY <= 0) {
                ballY = 0; // 상단 위치로 고정
                deltaY = -deltaY * energyLoss; // 반사 속도, 에너지 손실 반영
            }

            // 공이 왼쪽 벽에 닿았을 때
            if (ballX <= 0) {
                ballX = 0; // 왼쪽 위치로 고정
                deltaX = -deltaX * energyLoss; // 반사 속도, 에너지 손실 반영
            }

            // 공이 오른쪽 벽에 닿았을 때
            if (ballX + ballDiameter >= getWidth()) {
                ballX = getWidth() - ballDiameter; // 오른쪽 위치로 고정
                deltaX = -deltaX * energyLoss; // 반사 속도, 에너지 손실 반영
            }

            // 속도 정보를 업데이트
            statusLabel.setText(String.format("ΔX: %.2f ΔY: %.2f gravity: %.2f", deltaX, deltaY, gravity));

            // 패널을 다시 그려서 공의 새로운 위치를 표시
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // 마우스 클릭 시 드래그 시작 위치 저장
        dragStartPoint = e.getPoint();
        dragging = true;

        // 공의 위치를 클릭 위치로 이동
        ballX = dragStartPoint.x - ballDiameter / 2;
        ballY = dragStartPoint.y - ballDiameter / 2;

        // 드래그 시작 위치 초기화
        deltaX = 0;
        deltaY = 0;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (dragging && dragStartPoint != null) {
            // 드래그 종료 시의 마우스 위치를 가져와 속도를 계산
            Point releasePoint = e.getPoint();

            // 마우스 드래그의 시작과 종료 위치로부터 속도 계산
            double dx = releasePoint.getX() - dragStartPoint.getX();
            double dy = releasePoint.getY() - dragStartPoint.getY();


            // 속도를 설정
            deltaX = dx / 10; // 속도 조정 (변경 가능)
            deltaY = dy / 10; // 속도 조정 (변경 가능)

            // 중력 재설정
            gravity = 1;

            // 타이머 시작
            if (isPaused) {
                timer.start(); // 일시 정지 상태에서 다시 시작
                isPaused = false; // 일시 정지 플래그 초기화
            }

            dragging = false;
            dragStartPoint = null;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragging) {
            // 마우스 드래그 시 공의 위치를 마우스 위치로 업데이트
            Point currentPoint = e.getPoint();
            ballX = currentPoint.x - ballDiameter / 2;
            ballY = currentPoint.y - ballDiameter / 2;

            repaint(); // 화면 갱신
        }
    }

    // 미사용 메서드
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bouncing Ball");
        BounceTest ballPanel = new BounceTest(null);
        ballPanel.setPreferredSize(new Dimension(800, 400)); // 넓은 화면 크기 설정
        JScrollPane scrollPane = new JScrollPane(ballPanel);
        ballPanel.scrollPane = scrollPane; // 참조 설정

        frame.add(scrollPane);
        frame.setSize(1200, 600); // 보이는 화면 크기
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
