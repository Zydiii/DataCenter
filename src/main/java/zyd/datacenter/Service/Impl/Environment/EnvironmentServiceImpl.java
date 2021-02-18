package zyd.datacenter.Service.Impl.Environment;

import org.springframework.stereotype.Service;
import zyd.datacenter.Repository.Environment.EnvironmentRepository;
import zyd.datacenter.Service.Environment.EnvironmentService;

@Service
public class EnvironmentServiceImpl implements EnvironmentService {
    private EnvironmentRepository environmentRepository;

    public EnvironmentServiceImpl(EnvironmentRepository environmentRepository) {
        this.environmentRepository = environmentRepository;
    }
}
