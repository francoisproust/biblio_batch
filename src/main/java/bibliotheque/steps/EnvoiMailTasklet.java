package bibliotheque.steps;

import bibliotheque.service.RappelService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnvoiMailTasklet implements Tasklet {
    private final RappelService rappelService;

    @Autowired
    public EnvoiMailTasklet(RappelService rappelService){
        this.rappelService = rappelService;
    }
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        rappelService.envoyerRappelMails();
        return RepeatStatus.FINISHED;
    }
}
