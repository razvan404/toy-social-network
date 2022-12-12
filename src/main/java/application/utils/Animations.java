package application.utils;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public final class Animations {
    private static final double DEFAULT_TRANSITION_DURATION = 250;

    public static Animation changeWidthTransition(Pane anchorPane, double oldSize, double newSize, double duration) {
        return new Transition() {
            {
                setCycleDuration(Duration.millis(duration));
            }

            @Override
            protected void interpolate(double fraction) {
                anchorPane.setPrefWidth(oldSize * (1 - fraction) + newSize * fraction);
            }
        };
    }
    public static Animation changeWidthTransition(Pane anchorPane, double oldSize, double newSize) {
        return changeWidthTransition(anchorPane, oldSize, newSize, DEFAULT_TRANSITION_DURATION);

    }

    public static Animation changeHeightTransition(Pane anchorPane, double oldSize, double newSize, double duration) {
        return new Transition() {
            {
                setCycleDuration(Duration.millis(duration));
            }

            @Override
            protected void interpolate(double fraction) {
                anchorPane.setPrefHeight(oldSize * (1 - fraction) + newSize * fraction);
            }
        };
    }

    public static Animation changeHeightTransition(Pane anchorPane, double oldSize, double newSize) {
        return changeHeightTransition(anchorPane, oldSize, newSize, DEFAULT_TRANSITION_DURATION);
    }

    public static Animation changeBlurTransition(Node node, double oldBlur, double newBlur, double duration) {
        return new Transition() {
            {
                setCycleDuration(Duration.millis(duration));
            }

            @Override
            protected void interpolate(double fraction) {
                node.setEffect(new GaussianBlur(oldBlur * (1 - fraction) + newBlur * fraction));
            }
        };
    }
    public static Animation changeBlurTransition(Node node, double oldBlur, double newBlur) {
        return changeBlurTransition(node, oldBlur, newBlur, DEFAULT_TRANSITION_DURATION);
    }
}
