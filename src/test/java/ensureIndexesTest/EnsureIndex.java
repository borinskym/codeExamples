package ensureIndexesTest;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import mongo.HeaderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.IndexField;
import org.springframework.data.mongodb.core.index.IndexInfo;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import repository.SomeKindOfEntityWithCompoundIndex;
import repository.SomeKindOfRepository;
import testRestWithQueryParams.MongoRepositoryContext;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.mongodb.core.index.IndexField.create;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoRepositoryContext.class})
public class EnsureIndex {


    @EqualsAndHashCode
    @ToString
    @Builder
    private static class SparseIndexInfo{
        private String name;
        private boolean unique;
        private List<IndexField> indexFields;
        static SparseIndexInfo from(IndexInfo indexInfo) {
            return SparseIndexInfo.builder().name(indexInfo.getName()).unique(indexInfo.isUnique()).indexFields(indexInfo.getIndexFields()).build();
        }

        static SparseIndexInfo from(String name, boolean unique, List<IndexField> indexFields){
            return SparseIndexInfo.builder().name(name).unique(unique).indexFields(indexFields).build();
        }
    }

    @Autowired
    SomeKindOfRepository someKindOfRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void shouldEnsureIndex() {
        someKindOfRepository.save(new SomeKindOfEntityWithCompoundIndex());

        assertThat(sparseIndexes(), is(asList(
                SparseIndexInfo.from("_id_", false, singletonList(create("_id", ASC))),
                SparseIndexInfo.from("TriggerHeader", false, expectedIndexFields()))));
    }

    private List<SparseIndexInfo> sparseIndexes() {
        return mongoTemplate.indexOps(HeaderDTO.class).getIndexInfo().stream()
                .map(SparseIndexInfo::from)
                .collect(Collectors.toList());
    }


    private List<IndexField> expectedIndexFields() {
        return asList(
                IndexField.create("id", ASC),
                IndexField.create("eventTime", ASC),
                IndexField.create("type", ASC),
                IndexField.create("lastModified", ASC),
                IndexField.create("trigger", ASC),
                IndexField.create("origin", ASC)
        );
    }
}
