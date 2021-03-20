package zyd.datacenter.Controllers.EnvironmentManage;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/manage/environment")
@Api(value = "环境接口", tags="环境接口")
public class EnvironmentMangeController {
}
