package zyd.datacenter.Controllers.FittingsManage;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/manage/fitting")
@Api(value = "配件接口", tags="配件接口")
public class FittingManageController {
}
