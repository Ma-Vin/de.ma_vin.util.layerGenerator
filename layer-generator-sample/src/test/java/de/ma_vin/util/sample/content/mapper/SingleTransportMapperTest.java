package de.ma_vin.util.sample.content.mapper;

import de.ma_vin.util.sample.content.domain.IIdentifiable;
import de.ma_vin.util.sample.content.domain.Root;
import de.ma_vin.util.sample.content.domain.single.SingleRefOneParent;
import de.ma_vin.util.sample.content.domain.single.SingleRefTwoParents;
import de.ma_vin.util.sample.content.dto.ITransportable;
import de.ma_vin.util.sample.content.dto.RootDto;
import de.ma_vin.util.sample.content.dto.domain.DerivedFromDomainDto;
import de.ma_vin.util.sample.content.dto.single.SingleRefOneParentDto;
import de.ma_vin.util.sample.content.dto.single.SingleRefTwoParentsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static de.ma_vin.util.sample.content.ObjectFactory.*;
import static org.junit.jupiter.api.Assertions.*;

public class SingleTransportMapperTest {
    private Root root;
    private SingleRefOneParent singleRefOneParent;
    private SingleRefTwoParents singleRefTwoParents;

    private RootDto rootDto;
    private SingleRefOneParentDto singleRefOneParentDto;
    private SingleRefTwoParentsDto singleRefTwoParentsDto;
    private DerivedFromDomainDto derivedFromDomainDto;

    Map<String, IIdentifiable> mappedObjects = new HashMap<>();
    Map<String, ITransportable> mappedDtoObjects = new HashMap<>();

    @BeforeEach
    public void setUp() {
        mappedObjects.clear();
        mappedDtoObjects.clear();

        initObjectFactory();
        singleRefOneParent = createSingleRefOneParentWithChildren(getNextId());
        singleRefTwoParents = createSingleRefTwoParents(getNextId());
        root = createRoot(getNextId());

        initObjectFactory();
        singleRefOneParentDto = createSingleRefOneParentDtoWithChildren(getNextId());
        singleRefTwoParentsDto = createSingleRefTwoParentsDto(getNextId());
        rootDto = createRootDto(getNextId());
        derivedFromDomainDto = createDerivedFromDomainDto(getNextId());
        derivedFromDomainDto.setIdentification(singleRefOneParentDto.getIdentification());
        derivedFromDomainDto.setSingleRef(singleRefTwoParentsDto);
    }

    @Test
    public void testConvertToSingleRefOneParent() {
        SingleRefOneParent result = SingleTransportMapper.convertToSingleRefOneParent(singleRefOneParentDto);
        assertNotNull(result, "There should be any result");
        assertEquals(singleRefOneParentDto.getIdentification(), result.getIdentification(), "Wrong identification");
        assertEquals(singleRefOneParentDto.getDescription(), result.getDescription(), "Wrong description");
    }

    @Test
    public void testConvertToSingleRefOneParentWithParent() {
        SingleRefOneParent result = SingleTransportMapper.convertToSingleRefOneParent(singleRefOneParentDto, root);
        assertNotNull(result, "There should be any result");
        assertEquals(singleRefOneParentDto.getIdentification(), result.getIdentification(), "Wrong identification");
        assertEquals(singleRefOneParentDto.getDescription(), result.getDescription(), "Wrong description");

        assertEquals(result, root.getSingleRef(), "Wrong single ref at root");
    }

    @Test
    public void testConvertToSingleRefOneParentNull() {
        assertNull(SingleTransportMapper.convertToSingleRefOneParent(null), "The result should be null");
    }

    @Test
    public void testConvertToSingleRefOneParentAgain() {
        SingleRefOneParent result = SingleTransportMapper.convertToSingleRefOneParent(singleRefOneParentDto, mappedObjects);
        SingleRefOneParent convertAgainResult = SingleTransportMapper.convertToSingleRefOneParent(singleRefOneParentDto, mappedObjects);
        assertSame(result, convertAgainResult, "Converting again with map should return the same object");
    }

    @Test
    public void testConvertToSingleRefTwoParents() {
        SingleRefTwoParents result = SingleTransportMapper.convertToSingleRefTwoParents(singleRefTwoParentsDto);
        assertNotNull(result, "There should be any result");
        assertEquals(singleRefTwoParentsDto.getIdentification(), result.getIdentification(), "Wrong identification");
        assertEquals(singleRefTwoParentsDto.getDescription(), result.getDescription(), "Wrong description");
    }

    @Test
    public void testConvertToSingleRefTwoParentsWithRootParent() {
        SingleRefTwoParents result = SingleTransportMapper.convertToSingleRefTwoParents(singleRefTwoParentsDto, root);
        assertNotNull(result, "There should be any result");
        assertEquals(singleRefTwoParentsDto.getIdentification(), result.getIdentification(), "Wrong identification");
        assertEquals(singleRefTwoParentsDto.getDescription(), result.getDescription(), "Wrong description");

        assertEquals(result, root.getAnotherSingleRef(), "Wrong another single ref at root");
    }

    @Test
    public void testConvertToSingleRefTwoParentsWithOtherParent() {
        SingleRefTwoParents result = SingleTransportMapper.convertToSingleRefTwoParents(singleRefTwoParentsDto, singleRefOneParent);
        assertNotNull(result, "There should be any result");
        assertEquals(singleRefTwoParentsDto.getIdentification(), result.getIdentification(), "Wrong identification");
        assertEquals(singleRefTwoParentsDto.getDescription(), result.getDescription(), "Wrong description");

        assertEquals(result, singleRefOneParent.getSingleRef(), "Wrong single ref at root");
    }

    @Test
    public void testConvertToSingleRefTwoParentsNull() {
        assertNull(SingleTransportMapper.convertToSingleRefTwoParents(null), "The result should be null");
    }

    @Test
    public void testConvertToSingleRefTwoParentsAgain() {
        SingleRefTwoParents result = SingleTransportMapper.convertToSingleRefTwoParents(singleRefTwoParentsDto, mappedObjects);
        SingleRefTwoParents convertAgainResult = SingleTransportMapper.convertToSingleRefTwoParents(singleRefTwoParentsDto, mappedObjects);
        assertSame(result, convertAgainResult, "Converting again with map should return the same object");
    }

    @Test
    public void testConvertToSingleRefOneParentDto() {
        SingleRefOneParentDto result = SingleTransportMapper.convertToSingleRefOneParentDto(singleRefOneParent);
        assertNotNull(result, "There should be any result");
        assertEquals(singleRefOneParent.getIdentification(), result.getIdentification(), "Wrong identification");
        assertEquals(singleRefOneParent.getDescription(), result.getDescription(), "Wrong description");
    }

    @Test
    public void testConvertToSingleRefOneParentDtoWithParent() {
        SingleRefOneParentDto result = SingleTransportMapper.convertToSingleRefOneParentDto(singleRefOneParent, rootDto);
        assertNotNull(result, "There should be any result");
        assertEquals(singleRefOneParent.getIdentification(), result.getIdentification(), "Wrong identification");
        assertEquals(singleRefOneParent.getDescription(), result.getDescription(), "Wrong description");

        assertEquals(result, rootDto.getSingleRef(), "Wrong single ref at root");
    }

    @Test
    public void testConvertToSingleRefOneParentDtoNull() {
        assertNull(SingleTransportMapper.convertToSingleRefOneParentDto(null), "The result should be null");
    }

    @Test
    public void testConvertToSingleRefOneParentDtoAgain() {
        SingleRefOneParentDto result = SingleTransportMapper.convertToSingleRefOneParentDto(singleRefOneParent, mappedDtoObjects);
        SingleRefOneParentDto convertAgainResult = SingleTransportMapper.convertToSingleRefOneParentDto(singleRefOneParent, mappedDtoObjects);
        assertSame(result, convertAgainResult, "Converting again with map should return the same object");
    }

    @Test
    public void testConvertToSingleRefTwoParentsDto() {
        SingleRefTwoParentsDto result = SingleTransportMapper.convertToSingleRefTwoParentsDto(singleRefTwoParents);
        assertNotNull(result, "There should be any result");
        assertEquals(singleRefTwoParentsDto.getIdentification(), result.getIdentification(), "Wrong identification");
        assertEquals(singleRefTwoParentsDto.getDescription(), result.getDescription(), "Wrong description");
    }

    @Test
    public void testConvertToSingleRefTwoParentsDtoWithRootParent() {
        SingleRefTwoParentsDto result = SingleTransportMapper.convertToSingleRefTwoParentsDto(singleRefTwoParents, rootDto);
        assertNotNull(result, "There should be any result");
        assertEquals(singleRefTwoParents.getIdentification(), result.getIdentification(), "Wrong identification");
        assertEquals(singleRefTwoParents.getDescription(), result.getDescription(), "Wrong description");

        assertEquals(result, rootDto.getAnotherSingleRef(), "Wrong another single ref at root");
    }

    @Test
    public void testConvertToSingleRefTwoParentsDtoWithOtherParent() {
        SingleRefTwoParentsDto result = SingleTransportMapper.convertToSingleRefTwoParentsDto(singleRefTwoParents, singleRefOneParentDto);
        assertNotNull(result, "There should be any result");
        assertEquals(singleRefTwoParents.getIdentification(), result.getIdentification(), "Wrong identification");
        assertEquals(singleRefTwoParents.getDescription(), result.getDescription(), "Wrong description");

        assertEquals(result, singleRefTwoParentsDto, "Wrong single ref at root");
    }

    @Test
    public void testConvertToSingleRefTwoParentsDtoNull() {
        assertNull(SingleTransportMapper.convertToSingleRefTwoParentsDto(null), "The result should be null");
    }

    @Test
    public void testConvertToSingleRefTwoParentsDtoAgain() {
        SingleRefTwoParentsDto result = SingleTransportMapper.convertToSingleRefTwoParentsDto(singleRefTwoParents, mappedDtoObjects);
        SingleRefTwoParentsDto convertAgainResult = SingleTransportMapper.convertToSingleRefTwoParentsDto(singleRefTwoParents, mappedDtoObjects);
        assertSame(result, convertAgainResult, "Converting again with map should return the same object");
    }

    @Test
    public void testConvertToSingleRefTwoParentsDtoWithOtherParentFromDerivedFrom() {
        SingleRefTwoParentsDto result = SingleTransportMapper.convertToSingleRefTwoParentsDto(singleRefTwoParents, derivedFromDomainDto);
        assertNotNull(result, "There should be any result");
        assertEquals(singleRefTwoParents.getIdentification(), result.getIdentification(), "Wrong identification");
        assertEquals(singleRefTwoParents.getDescription(), result.getDescription(), "Wrong description");

        assertEquals(result, singleRefTwoParentsDto, "Wrong single ref at root");
    }

    @Test
    public void testGetInstance() {
        SingleTransportMapper firstInstance = SingleTransportMapper.getInstance();
        assertNotNull(firstInstance, "An instance should be created");
        assertSame(firstInstance, SingleTransportMapper.getInstance(), "Any other instance should be the same");
    }
}
