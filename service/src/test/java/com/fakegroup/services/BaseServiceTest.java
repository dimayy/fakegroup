package com.fakegroup.services;

import com.fakegroup.dto.BaseProjectDto;
import com.fakegroup.entities.BaseProjectEntity;
import com.fakegroup.repository.BaseRepository;
import com.fakegroup.services.impl.BaseService;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Spy;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public abstract class BaseServiceTest<E extends BaseProjectEntity, D extends BaseProjectDto> {

    @Spy protected MapperFacade mapper = new DefaultMapperFactory.Builder().build().getMapperFacade();

    @Captor
    private ArgumentCaptor<E> entityCaptor;

    BaseService<E, D> service;

    abstract BaseRepository<E> getRepository();
    abstract E prepareEntity(Long id);
    abstract D prepareDto(Long id);

    private static final long ID = 791023L;

    @Test
    public void findById() {
        E expectingResult = prepareEntity(ID);
        doReturn(Optional.of(expectingResult)).when(getRepository()).findById(eq(ID));

        D result = service.findById(ID);
        D expectingDto = (D) mapper.map(expectingResult, result.getClass());

        assertEquals(result, expectingDto);
        verify(getRepository(), times(1)).findById(ID);
    }

    @Test
    public void create() {
        D toSave = prepareDto(null);

        doAnswer(returnsFirstArg()).when(getRepository()).refresh(any());
        doAnswer(returnsFirstArg()).when(getRepository()).save(any());

        D result = service.create(toSave);

        assertEquals(toSave, result);

        verify(getRepository(), times(1)).save(entityCaptor.capture());
        verify(getRepository(), times(1)).refresh(entityCaptor.capture());

        assertEquals(entityCaptor.getAllValues().get(0), entityCaptor.getAllValues().get(1));
    }

    @Test
    public void update() {
        D toSave = prepareDto(ID);

        doAnswer(returnsFirstArg()).when(getRepository()).refresh(any());
        doAnswer(returnsFirstArg()).when(getRepository()).save(any());
        doReturn(true).when(getRepository()).existsById(eq(ID));

        D result = service.update(ID, toSave);

        assertEquals(toSave, result);

        verify(getRepository(), times(1)).save(entityCaptor.capture());
        verify(getRepository(), times(1)).refresh(entityCaptor.capture());

        assertEquals(entityCaptor.getAllValues().get(0), entityCaptor.getAllValues().get(1));
    }

    @Test(expected = RuntimeException.class)
    public void updateWithDifferentIds() {
        D toSave = prepareDto(ID);

        doAnswer(returnsFirstArg()).when(getRepository()).refresh(any());
        doAnswer(returnsFirstArg()).when(getRepository()).save(any());
        doReturn(true).when(getRepository()).existsById(eq(ID));

        D result = service.update(ID + 1, toSave);

        assertEquals(toSave, result);

        verify(getRepository(), times(1)).save(entityCaptor.capture());
        verify(getRepository(), times(1)).refresh(entityCaptor.capture());

        assertEquals(entityCaptor.getAllValues().get(0), entityCaptor.getAllValues().get(1));
    }

    @Test(expected = RuntimeException.class)
    public void updateWithNonExistingEntity() {
        D toSave = prepareDto(ID);

        doAnswer(returnsFirstArg()).when(getRepository()).refresh(any());
        doAnswer(returnsFirstArg()).when(getRepository()).save(any());
        doReturn(false).when(getRepository()).existsById(eq(ID));

        D result = service.update(ID , toSave);

        assertEquals(toSave, result);

        verify(getRepository(), times(1)).save(entityCaptor.capture());
        verify(getRepository(), times(1)).refresh(entityCaptor.capture());

        assertEquals(entityCaptor.getAllValues().get(0), entityCaptor.getAllValues().get(1));
    }

    @Test
    public void partialUpdate() {
        D toSave = prepareDto(ID);

        doAnswer(returnsFirstArg()).when(getRepository()).refresh(any());
        doAnswer(returnsFirstArg()).when(getRepository()).save(any());
        doReturn(true).when(getRepository()).existsById(eq(ID));

        D result = service.partialUpdate(ID, toSave);

        assertEquals(toSave, result);

        verify(getRepository(), times(1)).save(entityCaptor.capture());
        verify(getRepository(), times(1)).refresh(entityCaptor.capture());

        assertEquals(entityCaptor.getAllValues().get(0), entityCaptor.getAllValues().get(1));
    }

    @Test(expected = RuntimeException.class)
    public void partialUpdateWithDifferentIds() {
        D toSave = prepareDto(ID);

        doAnswer(returnsFirstArg()).when(getRepository()).refresh(any());
        doAnswer(returnsFirstArg()).when(getRepository()).save(any());
        doReturn(true).when(getRepository()).existsById(eq(ID));

        D result = service.partialUpdate(ID + 1, toSave);

        assertEquals(toSave, result);

        verify(getRepository(), times(1)).save(entityCaptor.capture());
        verify(getRepository(), times(1)).refresh(entityCaptor.capture());

        assertEquals(entityCaptor.getAllValues().get(0), entityCaptor.getAllValues().get(1));
    }

    @Test(expected = RuntimeException.class)
    public void partialUpdateWithNonExistingEntity() {
        D toSave = prepareDto(ID);

        doAnswer(returnsFirstArg()).when(getRepository()).refresh(any());
        doAnswer(returnsFirstArg()).when(getRepository()).save(any());
        doReturn(false).when(getRepository()).existsById(eq(ID));

        D result = service.partialUpdate(ID , toSave);

        assertEquals(toSave, result);

        verify(getRepository(), times(1)).save(entityCaptor.capture());
        verify(getRepository(), times(1)).refresh(entityCaptor.capture());

        assertEquals(entityCaptor.getAllValues().get(0), entityCaptor.getAllValues().get(1));
    }

}
