package com.example.sandbox.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.sandbox.demo.dto.Entry;

import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api")
public class CrudController {
  @Autowired
  private JdbcTemplate db;

  @GetMapping("/entries")
  public List<Map<String, Object>> getEntries() {
    return db.queryForList("SELECT id as \"id\", title as \"title\", description as \"description\" FROM entries");
  }

  @DeleteMapping("/entries/{id}")
  public List<Map<String, Object>> deleteEntries(@PathVariable long id) {
    db.update("DELETE FROM entries WHERE id = ?", id);
    return db.queryForList("SELECT id as \"id\", title as \"title\", description as \"description\" FROM entries");
  }

  @PostMapping("/entries")
  public Map<String,String> createEntry(@RequestBody Entry entry) {
    db.update("INSERT INTO entries(id, title, description) VALUES(?, ?, ?)", entry.getId(), entry.getTitle(), entry.getDescription());
    return Map.of("status", "success");
  }

  @PatchMapping("/entries")
  public Map<String,String> updateEntry(@RequestBody Entry entry) {
    db.update("UPDATE entries SET title = ?, description = ? WHERE id = ?",  entry.getTitle(), entry.getDescription(), entry.getId());
    return Map.of("status", "success");
  }
  

  @PostConstruct
  public void initDb() {
    String createTableCommand = "CREATE TABLE entries (id BIGINT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255), description VARCHAR(255))";
    db.execute(createTableCommand);

    String[][] initialData = {
      {"Familiar Colours, Hidden Intent", "Along the coastal cliffs, we met dragonborn marines, mounted on winged beasts, their banners imperial. They acknowledged our presence with nods, but their intent was inscrutable. Even allies carry weight in these borderlands."},
      {"The Weight of Purpose", "Each report we send feels meaningless, as though the truth we uncover will not satisfy the king or worse, that the realm expects something we cannot comprehend. Scouts ride in silence, each carrying their own unease."}
    };

    for (int i = 0; i < initialData.length; i++) {
      db.execute("INSERT INTO entries(title, description) VALUES('" + initialData[i][0] + "', '" + initialData[i][1] + "')");
    }

  }
}
