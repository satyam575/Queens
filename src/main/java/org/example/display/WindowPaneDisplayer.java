package org.example.display;

import org.example.model.Board;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class WindowPaneDisplayer implements Displayer{
    @Override
    public void display(Board board, boolean[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        int cellSize = 100; // size of each cell
        int imgWidth = cols * cellSize;
        int imgHeight = rows * cellSize;

        // Create a white canvas
        Mat img = Mat.ones(imgHeight, imgWidth, CvType.CV_8UC3);
        img.setTo(new Scalar(255, 255, 255)); // white background

        // Draw grid
        for (int i = 0; i <= rows; i++) {
            Imgproc.line(img, new Point(0, i * cellSize), new Point(imgWidth, i * cellSize), new Scalar(0, 0, 0), 2);
        }
        for (int j = 0; j <= cols; j++) {
            Imgproc.line(img, new Point(j * cellSize, 0), new Point(j * cellSize, imgHeight), new Scalar(0, 0, 0), 2);
        }

        // Draw dots or Qs for queens
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j]) {
                    int centerX = j * cellSize + cellSize / 2;
                    int centerY = i * cellSize + cellSize / 2;

//                    Imgproc.circle(img, new Point(centerX, centerY), 10, new Scalar(0, 0, 0), -1);
                    Imgproc.putText(img, "Q", new Point(centerX - 15, centerY + 15),
                            Imgproc.FONT_HERSHEY_SIMPLEX, 1.0, new Scalar(0, 0, 0), 2);
                }
            }
        }

        // Convert Mat to BufferedImage
        BufferedImage bufferedImage = matToBufferedImage(img);

        // Display in JFrame
        JFrame frame = new JFrame("Queens Game Solution");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel(new ImageIcon(bufferedImage));
        frame.getContentPane().add(label);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);
    }
    private static BufferedImage matToBufferedImage(Mat mat) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        mat.get(0, 0, ((DataBufferByte) image.getRaster().getDataBuffer()).getData());
        return image;
    }
}
