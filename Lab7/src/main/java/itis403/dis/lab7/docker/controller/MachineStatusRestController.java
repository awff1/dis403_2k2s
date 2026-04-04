package itis403.dis.lab7.docker.controller;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


@RestController
public class MachineStatusRestController {

//    private Logger logger = LoggerFactory.getLogger()

    private MachineStatusService machineStatusService;

    public MachineStatusRestController(MachineStatusService machineStatusService) {
        this.machineStatusService = machineStatusService;
    }

    @GetMapping("/api/status/{id}")
    public ResponseEntity<MachineStatus> getMachineStatus(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(machineStatusService.getStatus(id));
    }

    @PostMapping("/api/resource")
    public ResponseEntity<String> setResource(@RequestParam("resource") Double resource, @RequestParam("id") Integer id) {
        return ResponseEntity.ok("{\"status\":\"success\"}");
    }
}

