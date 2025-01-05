package com.jpacourse.service.impl;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.PatientVisitsTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.mapper.VisitMapper;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class PatientServiceImpl implements PatientService
{
    private final PatientDao patientDao;

    @Autowired
    public PatientServiceImpl(PatientDao pPatientDao)
    {
        patientDao = pPatientDao;
    }

    @Override
    public PatientTO findById(Long id) {
        final PatientEntity entity = patientDao.findOne(id);
        return PatientMapper.mapToTO(entity);
    }

    @Override
    public void deleteById(Long id) {
        final PatientEntity entity = patientDao.findOne(id);
        if(entity == null)
        {
            throw new EntityNotFoundException(id);
        } else {
            patientDao.delete(entity);
        }
    }

    @Override
    public PatientVisitsTO findVisitsByPatientId(final Long patientId){
        final PatientEntity entity = patientDao.findVisitsForPatientId(patientId);

        return PatientVisitsTO.builder()
                .visits(entity.getVisits().stream().map(VisitMapper::mapToTO).collect(Collectors.toList()))
                .build();
//        return entity.stream().map(PatientMapper::mapToTO).collect(Collectors.toList());
    }

}
