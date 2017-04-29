window.addEventListener('load', () => {
    let word      = document.querySelector('.word');
    let letter    = document.querySelector('.letter-input');
    let newGame   = document.querySelector('.new-game');
    let guess     = document.querySelector('.guess');
    let guesses   = document.querySelector('.guesses');
    let remaining = document.querySelector('.remaining');
    let status    = document.querySelector('.status');
    let hangdude  = document.querySelector('.dude');

    newGame.addEventListener('click', (e) => {
        fetch('/new-game', { method: 'POST', credentials: 'include' }).then(function (response) {
            response.json().then(updateWordText);
        });

        guess.disabled = false;
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

    letter.addEventListener('keyup', (e) => {
        if (e.keyCode === 13 && e.target.value !== '') {
            let me = new MouseEvent('click');

            if (word.innerText.indexOf('_') === -1) {
                newGame.dispatchEvent(me);
            } else {
                guess.dispatchEvent(me);
            }

            empty(letter);
        }
    });

    letter.addEventListener('focus', (e) => {
        empty(letter);
    });

    function empty(element) {
        element.value = '';
    }

    function updateWordText(data) {
        word.innerText = data.word;
        remaining.innerText = data.remainingGuesses;
        guesses.innerText = data.guesses;

        if (data.remainingGuesses === 0) {
            status.innerText = 'Game Over. The dude is hanged.';
            guess.disabled = true;
        } else if (data.word.indexOf("_") === -1) {
            status.innerText = 'YEAH YOU TOTALLY WON YES';
            guess.disabled = true;
        }

        hangdude.innerText = data.hangmanStatus;
    }


    fetch('/game', { credentials: 'include' }).then(response => {
        response.json().then(data => {
            if (data === '') {
                newGame.dispatchEvent(new MouseEvent('click'));
            } else {
                updateWordText(data);
                guess.disabled = false;
            }
        });
    });

    letter.focus();
});