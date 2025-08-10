package br.com.ifba.infrastructure.entity.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObjectMapperUtil {

    private final ModelMapper modelMapper;

    public ObjectMapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <S, T> T map(final S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }

    public <S, T> List<T> mapAll(final List<S> sourceList, Class<T> targetClass) {
        return sourceList.stream()
                .map(entity -> map(entity, targetClass))
                .collect(Collectors.toList());
    }
}
