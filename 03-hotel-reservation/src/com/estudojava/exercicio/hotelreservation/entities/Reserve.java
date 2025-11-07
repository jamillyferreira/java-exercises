package com.estudojava.exercicio.hotelreservation.entities;

import com.estudojava.exercicio.hotelreservation.exceptions.InvalidDateException;
import com.estudojava.exercicio.hotelreservation.exceptions.InvalidReserveException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Reserve {
    private String guestName;
    private LocalDate checkIn;
    private LocalDate checkOut;

    // Formato usado para mostrar as datas no toString()
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Construtor da reserva
    // Aqui já são feitas as validações antes de criar o objeto
    public Reserve(String guestName, LocalDate checkIn, LocalDate checkOut)
            throws InvalidDateException, InvalidReserveException {
        validateName(guestName);
        validateDates(checkIn, checkOut);
        this.guestName = guestName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public void validateName(String guestName)
            throws InvalidReserveException {
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidReserveException("O nome do hóspede não pode ser vazio.");
        }
    }

    // Valida se as datas são válidas
    // Aqui fica as regras de negócio relacionadas a reserva
    public void validateDates(LocalDate checkIn, LocalDate checkOut)
            throws InvalidDateException, InvalidReserveException {

        if (checkOut.isBefore(checkIn) || checkOut.isEqual(checkIn)) {
            throw new InvalidDateException("Check-out deve ser após o check-in ");
        }

        // Cálculo da duração da estadia
        long duration = ChronoUnit.DAYS.between(checkIn, checkOut);
        if (duration > 30) {
            throw new InvalidReserveException("Reserva não pode exceder 30 dias");
        }

    }
    // Atualiza as datas da reserva
    // Antes de atualizar, valida novamente
    public void updateDates(LocalDate newCheckIn, LocalDate newCheckOut)
            throws InvalidDateException, InvalidReserveException {
        validateDates(newCheckIn, newCheckOut);
        this.checkIn = newCheckIn;
        this.checkOut = newCheckOut;
    }

    // Calcula e retorna a duração da reserva em dias
    public long getDuration() {
        return ChronoUnit.DAYS.between(checkIn, checkOut);

    }

    @Override
    public String toString() {
        return "Reserva: " + guestName
                + " | Check-in: " + checkIn.format(FMT)
                + " | Check-out: " + checkOut.format(FMT)
                + " | " + getDuration() + " Diária(s)";
    }
}
