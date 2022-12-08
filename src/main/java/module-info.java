module application.gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens application.gui to javafx.fxml;
    exports application.gui;
    exports application.controller;
    exports application.controller.exceptions;
    exports application.service;
    exports application.service.exceptions;
    exports application.repository;
    exports application.domain;
    exports application.domain.exceptions;
    exports application.utils;
    exports application.utils.pair;
    exports application.controller.windows;
    exports application.controller.list;
}