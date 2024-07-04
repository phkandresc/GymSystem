package org.gimnasio.daos;

import java.sql.SQLException;
import java.util.List;

public interface CRUD<T>{
    List<T> obtenerListaDeDatos() throws SQLException;
    boolean agregarDato(T dato) throws SQLException;
    boolean actualizarDato(T dato) throws SQLException;
    boolean eliminarDato(T dato) throws SQLException;
    T buscarDatoPorId(int id) throws SQLException;
}
