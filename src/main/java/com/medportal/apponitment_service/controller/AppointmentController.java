package com.medportal.apponitment_service.controller;

import com.medportal.apponitment_service.entity.Appointment;
import com.medportal.apponitment_service.repository.AppointmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;

    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        appointment.setStatus("SCHEDULED");
        return appointmentRepository.save(appointment);
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + id));
    }
}
