package com.example.membership.job.pass;

import com.example.membership.model.BulkPassEntity;
import com.example.membership.model.PassEntity;
import com.example.membership.model.UserGroupMappingEntity;
import com.example.membership.repository.BulkPassRepository;
import com.example.membership.repository.PassRepository;
import com.example.membership.repository.UserGroupMappingRepository;
import com.example.membership.status.BulkPassStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class AddPassesTasklet implements Tasklet {

    private final PassRepository passRepository;
    private final BulkPassRepository bulkPassRepository;
    private final UserGroupMappingRepository userGroupMappingRepository;

    public AddPassesTasklet(PassRepository passRepository, BulkPassRepository bulkPassRepository, UserGroupMappingRepository userGroupMappingRepository) {
        this.passRepository = passRepository;
        this.bulkPassRepository = bulkPassRepository;
        this.userGroupMappingRepository = userGroupMappingRepository;
    }


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        final LocalDateTime startedAt = LocalDateTime.now().minusDays(1);
        final List<BulkPassEntity> bulkPassEntities = bulkPassRepository.findByStatusAndRegDateGreaterThan(BulkPassStatus.READY, startedAt);

        int count = 0;

        for(BulkPassEntity entity : bulkPassEntities){
            final List<String> userIds = userGroupMappingRepository.findByUserGroupId(entity.getUserGroupId())
                    .stream().map(UserGroupMappingEntity::getUserId).toList();

            count += addPasses(entity, userIds);

            entity.setStatus(BulkPassStatus.COMPLETED);
        }

        return RepeatStatus.FINISHED;
    }

    private int addPasses(BulkPassEntity bulkPassEntity, List<String> userIds){
        List<PassEntity> passEntities = new ArrayList<>();
        for(String userId : userIds){
            PassEntity passEntity = bulkPassEntity.convert(userId);
            passEntities.add(passEntity);
        }
        return passRepository.saveAll(passEntities).size();
    }
}
