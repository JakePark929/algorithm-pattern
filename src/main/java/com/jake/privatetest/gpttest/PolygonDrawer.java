package com.jake.privatetest.gpttest;

import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

public class PolygonDrawer extends JFrame {
    private int n;  // 다각형의 변의 수

    public PolygonDrawer(int n) {
        this.n = n;

        setTitle("다각형과 각 꼭지점을 이어주는 선 그리기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int centerX = getWidth() / 2;  // 중심 좌표 X
        int centerY = getHeight() / 2;  // 중심 좌표 Y
        int radius = 300;  // 반지름

        int[] xPoints = new int[n];  // X 좌표 배열
        int[] yPoints = new int[n];  // Y 좌표 배열

        // 다각형의 각 꼭지점 좌표 계산
        for (int i = 0; i < n; i++) {
            double angle = 2 * Math.PI * i / n;  // 꼭지점의 각도
            xPoints[i] = (int) (centerX + radius * Math.cos(angle));
            yPoints[i] = (int) (centerY + radius * Math.sin(angle));
        }

        g.drawPolygon(xPoints, yPoints, n);  // 다각형 그리기

        for (int i = 0; i < n - 2; i++) {
            for(int j = i + 2; j < n; j++) {
                if(!(i == 0 && j == n - 1)) {
                    g.drawLine(xPoints[i], yPoints[i], xPoints[j], yPoints[j]);
                }
            }
        }

        // 폴리곤 안그리고 그리기
//        for (int i = 0; i < n; i++) {
//            for(int j = i + 1; j < n; j++) {
//                g.drawLine(xPoints[i], yPoints[i], xPoints[j], yPoints[j]);
//            }
//        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("몇각형의 도형을 그릴까요: ");
        int n = sc.nextInt();  // 다각형의 변의 수를 명령행 인수로 받음
        PolygonDrawer drawer = new PolygonDrawer(n);
    }
}
