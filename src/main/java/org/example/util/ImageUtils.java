package org.example.util;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class ImageUtils {

    public static List<MatOfPoint> findContours(Mat edges) {
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        return contours;
    }

    public static Rect findBiggestRectangle(List<MatOfPoint> contours) {
        double maxArea = -1;
        Rect biggest = null;
        for (MatOfPoint contour : contours) {
            Rect rect = Imgproc.boundingRect(contour);
            double area = rect.width * rect.height;
            if (area > maxArea) {
                maxArea = area;
                biggest = rect;
            }
        }
        return biggest;
    }
}
