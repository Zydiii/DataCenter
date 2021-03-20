package zyd.datacenter.Controllers.FlightManage;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/manage/flight")
@Api(value = "飞行器接口", tags="飞行器接口")
public class FlightManageController {
}
