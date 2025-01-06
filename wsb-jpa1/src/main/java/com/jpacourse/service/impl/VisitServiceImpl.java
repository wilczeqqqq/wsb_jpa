package com.jpacourse.service.impl;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.mapper.VisitMapper;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.service.VisitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class VisitServiceImpl implements VisitService
{
    private final VisitDao visitDao;

    @Autowired
    public VisitServiceImpl(VisitDao pVisitDao)
    {
        visitDao = pVisitDao;
    }

    @Override
    public List<VisitTO> findVisitsByPatientId(final Long patientId){
        final List<VisitEntity> entity = visitDao.findVisitsForPatientId(patientId);
        return entity.stream()
                .map(VisitMapper::mapToTO)
                .collect(Collectors.toList());
    }
}
