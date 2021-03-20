package zyd.datacenter.Controllers.WeaponManage;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/manage/weapon")
@Api(value = "武器管理", tags="武器管理")
public class WeaponManageController {

}
