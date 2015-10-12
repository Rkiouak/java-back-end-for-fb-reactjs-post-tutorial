package fbReact;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class CommentController {

	@Autowired
    JdbcTemplate jdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
	@RequestMapping(method=RequestMethod.GET, value="/comments")
    public List greeting(){
    	log.info("Querying for all comments.");
    	return jdbcTemplate.query(
                "SELECT * FROM comments",
                (rs, rowNum) -> new Comment(rs.getString("author"), rs.getString("text"))
        );
    }

    @RequestMapping(method=RequestMethod.POST, value="/comments")
    public List greeting(@RequestParam(value="text") String text, @RequestParam(value="author") String author) {
        log.info("Comment inserted.");
        List<Object[]> list = new ArrayList<Object[]>();
        Object[] map = new Object[2];
        map[0]=author;
        map[1]=text;
        list.add(map);

    	jdbcTemplate.batchUpdate(
                "INSERT INTO comments(author, text) VALUES (?,?)",
                list
        );
        return jdbcTemplate.query(
                "SELECT * FROM comments",
                (rs, rowNum) -> new Comment(rs.getString("author"), rs.getString("text"))
        );
    }
}