package breakthrough.server;

import breakthrough.domain.BreakthroughSurrogate;
import breakthrough.domain.Move;
import breakthrough.domain.Position;
import breakthrough.main.ResponseObject;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import spark.Response;

import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by csdev on 11/27/17.
 */
public class TestServerRestAdapter {

    ServerRestAdapter adapter;
    Gson gson;
    @Before
    public void setup(){
        adapter = new ServerRestAdapter();
        gson = new Gson();
    }

    @Test
    public void shouldCreateCorrectRoOnPost(){
        ResponseObject ro = new ResponseObject();
        ro.setBody(42+"");
        ro.setStatus(HttpServletResponse.SC_CREATED);
        ro.addHeader("Location", "/breakthrough/create");

        ResponseObject ro2 = adapter.postOnBreakthrough();

        assertThat(ro2.getBody(), is(ro.getBody()));
        assertThat(ro2.getHeader("Location"), is(ro.getHeader("Location")));
        assertThat(ro2.getStatus(), is(ro.getStatus()));
    }

    @Test
    public void shouldCreateCorrectRoOnPut(){

        adapter.postOnBreakthrough();
        Move move = new Move(new Position(7,7), new Position(6,7));

        ResponseObject ro2 = adapter.putOnBreakthrough("42", move);
        ResponseObject ro = new ResponseObject();
        ro.setBody(gson.toJson(move));
        ro.setStatus(HttpServletResponse.SC_ACCEPTED);

        BreakthroughSurrogate game = new BreakthroughSurrogate();


        assertThat(game.getPlayerInTurn(), is(game.getBoardState().get(move.getFrom())));
        assertThat(game.move(move), is(true));

        assertThat(ro.getStatus(), is(ro2.getStatus()));
        assertThat(ro.getBody(), is(ro.getBody()));
    }

    @Test
    public void shouldCreateCorrectRoOnGet(){
        adapter.postOnBreakthrough();
        BreakthroughSurrogate game = new BreakthroughSurrogate();

        ResponseObject ro2 = adapter.getOnBreakthrough("42");
        ResponseObject ro = new ResponseObject();

        ro.setStatus(HttpServletResponse.SC_OK);
        ro.setBody(gson.toJson(game));

        assertThat(ro2.getBody(), is(ro.getBody()));
        assertThat(ro2.getStatus(), is(ro.getStatus()));
    }

}
