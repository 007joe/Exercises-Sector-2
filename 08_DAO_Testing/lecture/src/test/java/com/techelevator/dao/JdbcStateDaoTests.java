package com.techelevator.dao;

import com.techelevator.model.State;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class JdbcStateDaoTests extends BaseDaoTests {

    private static final State STATE_A = new State("AA", "State A");
    private static final State STATE_B = new State("BB", "State B");
    private static final State STATE_C = new State("CC", "State C");

    private JdbcStateDao sut;

    @Before
    public void setup() {
        sut = new JdbcStateDao(dataSource);
    }

    @Test
    public void getState_returns_correct_state_for_abbreviation() {
        State state = sut.getState("AA");
        assertStatesMatch(STATE_A, state);

        state = sut.getState("BB");
        assertStatesMatch(STATE_B, state);
    }

    @Test
    public void getState_returns_null_when_abbreviation_not_found() {
        State state = sut.getState("XX");
        Assert.assertNull(state);
    }

    @Test
    public void getStateByCapital_returns_correct_state_for_capital_id() {
        State state = sut.getStateByCapital(1);
        assertStatesMatch(STATE_A, state);

        state = sut.getStateByCapital(2);
        assertStatesMatch(STATE_B, state);
    }

    @Test
    public void getStateByCapital_returns_null_for_non_capital_city_id() {
        State state = sut.getStateByCapital(99); //not in table
        Assert.assertNull(state);

        state = sut.getStateByCapital(4); //in table, but not capital
        Assert.assertNull(state);
    }

    @Test
    public void getStates_returns_all_states() {
        List<State> states = sut.getStates();

        Assert.assertEquals(3, states.size());

        assertStatesMatch(STATE_A, states.get(0));
        assertStatesMatch(STATE_B, states.get(1));
        assertStatesMatch(STATE_C, states.get(2));
    }

    private void assertStatesMatch(State expected, State actual) {
        Assert.assertEquals(expected.getStateAbbreviation(), actual.getStateAbbreviation());
        Assert.assertEquals(expected.getStateName(), actual.getStateName());
    }
}
