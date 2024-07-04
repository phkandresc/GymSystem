package org.gimnasio.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowController extends WindowAdapter{
    protected PaginaPrincipalController paginaPrincipalController;

    public WindowController() {
        paginaPrincipalController = PaginaPrincipalController.getInstance();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        e.getWindow().dispose();
        paginaPrincipalController.mostrarPaginaPrincipal();
    }
}

