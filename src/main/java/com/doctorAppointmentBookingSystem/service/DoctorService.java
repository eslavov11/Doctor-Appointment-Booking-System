package com.doctorAppointmentBookingSystem.service;

import com.doctorAppointmentBookingSystem.entity.Doctor;
import com.doctorAppointmentBookingSystem.model.bindingModel.DoctorRegistrationModel;
import com.doctorAppointmentBookingSystem.model.bindingModel.EditDoctorModel;
import com.doctorAppointmentBookingSystem.model.bindingModel.EditDoctorPictureModel;
import com.doctorAppointmentBookingSystem.model.viewModel.DoctorSelectViewModel;
import com.doctorAppointmentBookingSystem.model.viewModel.DoctorViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Edi on 16-Apr-17.
 */
public interface DoctorService {
    void create(DoctorRegistrationModel registrationModel);

    void save(EditDoctorModel editDoctorModel);

    void savePicture(EditDoctorPictureModel editDoctorPictureModel);

    Doctor getById(long id);

    DoctorViewModel getViewModelById(long id);

    EditDoctorPictureModel getPictureModelByUserId(long id);

    Doctor getByUserId(long userId);

    EditDoctorModel getEditModelByUserId(long userId);

    DoctorSelectViewModel getModelByUserId(long userId);

    List<DoctorSelectViewModel> getAllSelect();

    Page<DoctorViewModel> getAll(Pageable pageable);
}
