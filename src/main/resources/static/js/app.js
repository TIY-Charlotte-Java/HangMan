window.addEventListener('load', () => {
    let word      = document.querySelector('.word');
    let letter    = document.querySelector('.letter-input');
    let newGame   = document.querySelector('.new-game');
    let guess     = document.querySelector('.guess');
    let guessWord = document.querySelector('.guess-whole');
    let guesses   = document.querySelector('.guesses');
    let remaining = document.querySelector('.remaining');
    let status    = document.querySelector('.status');
    let hangdude  = document.querySelector('.dude');

    newGame.addEventListener('click', (e) => {
        fetch('/new-game', { method: 'POST', credentials: 'include' }).then(function (response) {
            response.json().then(updateWordText);
        });

        setGuessesEnabled(true);
        status.innerText = '';
        guesses.innerText = '';
    });

    guess.addEventListener('click', (e) => {
        if (letter.value !== '') {
            fetch('/guess-letter', { method: 'POST', credentials: 'include', body: letter.value }).then(response => {
                response.json().then(updateWordText).then((e) => { letter.focus });
            });
        }
    });

    guessWord.addEventListener('click', (e) => {
        if (letter.value !== '') {
            fetch('/guess-word', { method: 'POST', credentials: 'include', body: letter.value }).then(response => {
                response.json().then(updateWordText).then((e) => { letter.focus });
            });
        }
    });

    letter.addEventListener('keyup', (e) => {
        let me = new MouseEvent('click');

        if (e.keyCode === 13) {
            if (word.innerText.indexOf('_') === -1) {
                newGame.dispatchEvent(me);
            } else if (e.target.value !== '') {
                guess.dispatchEvent(me);
                empty(letter);
            }
        }
    });

    letter.addEventListener('focus', (e) => {
        empty(letter);
    });

    function empty(element) {
        element.value = '';
    }

    function setGuessesEnabled(enabled) {
        guess.disabled = !enabled;
        guessWord.disabled = !enabled;
    }

    function updateWordText(data) {
        word.innerText = data.word;
        guesses.innerText = data.guesses;

        if (data.remainingGuesses === 0) {
            status.innerText = 'Game Over. The dude is hanged.';
            setGuessesEnabled(true);
        } else if (data.word.indexOf("_") === -1) {
            status.innerText = 'YEAH YOU TOTALLY WON YES';
            setGuessesEnabled(true);
        }

        hangdude.innerText = data.hangmanStatus;
    }


    fetch('/game', { credentials: 'include' }).then(response => {
        response.json().then(data => {
            if (data === '') {
                newGame.dispatchEvent(new MouseEvent('click'));
            } else {
                updateWordText(data);
                setGuessesEnabled(true);
            }
        });
    });

    letter.focus();
});