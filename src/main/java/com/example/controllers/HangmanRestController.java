package com.example.controllers;

import com.example.models.Game;
import com.example.services.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by Ben on 4/14/17.
 */

@RestController
public class HangmanRestController {
    @Autowired
    WordService wordService;

    @RequestMapping(method = RequestMethod.POST, path = "/new-game")
    public Game newGame(HttpSession session) {
        Game g = Game.createGame(wordService);
        session.setAttribute("game", g);
        return g;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/guess-letter")
    public Game updategame(HttpSession session, @RequestBody String letter) {
        Game g = (Game)session.getAttribute("game");

        g.guessLetter(letter);

        return g;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/game")
    public Game getGame(HttpSession session) {
        return (Game)session.getAttribute("game");
    }
}