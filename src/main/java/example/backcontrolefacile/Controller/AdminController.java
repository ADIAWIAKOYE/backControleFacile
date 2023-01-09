package example.backcontrolefacile.Controller;

import example.backcontrolefacile.Models.AppRole;
import example.backcontrolefacile.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/saverole")
    public AppRole saveRole(@RequestBody AppRole appRole){

        return adminService.addRole(appRole);
    }
}
