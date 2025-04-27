package org.example.extractor;

import nu.pattern.OpenCV;
import org.example.mapper.ColorRegionMapper;
import org.example.model.Board;
import org.example.util.ImageUtils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.List;

public class ImageBoardExtractor implements BoardExtractor {// 9x9 grid assumed

    @Override
    public Board extractBoard(String imagePath,int gridSize) {
        OpenCV.loadLocally(); // Load native libs

        Mat original = Imgcodecs.imread(imagePath);
        if (original.empty()) {
            throw new RuntimeException("Could not load image: " + imagePath);
        }

        // Preprocess
        Mat gray = new Mat();
        Imgproc.cvtColor(original, gray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.GaussianBlur(gray, gray, new Size(5, 5), 0);

        // Edge Detection
        Mat edges = new Mat();
        Imgproc.Canny(gray, edges, 50, 150);

        // Find outer box
        List<MatOfPoint> contours = ImageUtils.findContours(edges);
        Rect outerBox = ImageUtils.findBiggestRectangle(contours);

        if (outerBox == null) {
            throw new RuntimeException("Could not find outer box of board.");
        }

        int cellWidth = outerBox.width / gridSize;
        int cellHeight = outerBox.height / gridSize;

        int[][] matrix = new int[gridSize][gridSize];
        ColorRegionMapper colorMapper = new ColorRegionMapper();

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                int centerX = outerBox.x + j * cellWidth + cellWidth / 2;
                int centerY = outerBox.y + i * cellHeight + cellHeight / 2;

                double[] pixel = original.get(centerY, centerX);

                if (pixel == null) {
                    throw new RuntimeException("Center pixel access failed at (" + centerX + ", " + centerY + ")");
                }

                int region = colorMapper.getRegionForColor(pixel);
                matrix[i][j] = region;
            }
        }

        return new Board(gridSize,matrix);
    }
}