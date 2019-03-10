package config;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Map;

@Configuration
public class HashMapConfig {
    @Bean(value = "constants")
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public Map<String, Double> constants() {
        return ImmutableMap.<String, Double>builder()
                .put("fanAttitudeMMA", 0.1)
                .put("fighterAttackSpeed", 0.01)
                .put("fighterDefenseSpeed", 0.01)
                .put("punchForce", 0.00005)
                .put("punchSpeed", 0.002)
                .put("distance", 50.0)
                .put("goal", 0.2)
                .put("importantGoal", 0.2)
                .put("criticalGoal", 0.4)
                .put("save", 0.2)
                .put("importantSave", 0.2)
                .put("criticalSave", 0.4)
                .put("play", 0.2)
                .put("minutesRemaining", 5.0)
                .put("aggressiveness", 0.2)
                .put("danger", 0.2)
                .put("dominance", 0.4)
                .put("pace", 0.2)
                .put("noise", 0.1)
                .put("redCard", 0.2)
                .put("yellowCard", 0.05)
                .put("effect", 0.2)
                .put("possession", 0.2)
                .build();
    }
    //to use apply this
    @Qualifier("constants") Map<String, Double> constants;

}
