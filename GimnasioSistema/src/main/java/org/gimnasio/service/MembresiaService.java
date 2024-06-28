package org.gimnasio.service;

import org.gimnasio.daos.MembresiaDAO;
import org.gimnasio.model.Membresia;
import org.gimnasio.model.Socio;
import org.gimnasio.model.TipoMembresia;

import java.sql.Date;
import java.util.Calendar;

public class MembresiaService {
    private MembresiaDAO membresiaDAO;

    public MembresiaService() {
        this.membresiaDAO = new MembresiaDAO();
    }

    public Membresia registrarMembresia(Socio socio, TipoMembresia tipoMembresia) throws Exception {
        Calendar calendar = Calendar.getInstance();
        Date fechaInicio = new Date(calendar.getTimeInMillis());
        calendar.add(Calendar.DAY_OF_MONTH, tipoMembresia.getDuracion());
        Date fechaFin = new Date(calendar.getTimeInMillis());

        Membresia membresia = new Membresia(
                socio,
                tipoMembresia,
                1,
                fechaInicio,
                fechaFin
        );
        membresiaDAO.agregarDato(membresia);
        return membresia;
    }

}
