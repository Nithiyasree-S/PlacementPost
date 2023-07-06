package com.cg.sn.placement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.sn.placement.Entity.Placement;
import com.cg.sn.placement.Service.PlacementService;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/placement")
public class PlacementController {
    private final PlacementService placementService;

    @Autowired
    public PlacementController(PlacementService placementService) {
        this.placementService = placementService;
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addPlacement(@RequestBody Placement placement) {
        Placement addedPlacement = placementService.addPlacement(placement);
         Map<String, Object> response = new HashMap<>();
        response.put("data", addedPlacement);
         return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/posts")
    public ResponseEntity<Map<String, Object>> getAllPlacements() {
        List<Placement> placements = placementService.getAllPlacements();
        Map<String, Object> response = new HashMap<>();
        response.put("data", placements);
        return ResponseEntity.ok(response);
    }
}
