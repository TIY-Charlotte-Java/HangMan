window.onload = function () {
    let word      = document.querySelector('.word');
    let letter    = document.querySelector('.letter-input');
    let newGame   = document.querySelector('.new-game');
    let guess     = document.querySelector('.guess');
    let remaining = document.querySelector('.remaining');
    let status    = document.querySelector('.status');

    newGame.addEventListener('click', (e) => {
        fetch('/new-game', { method: 'POST', credentials: 'include' }).then(function (response) {
            response.json().then(updateWordText);
        });

        guess.disabled = false;
        status.innerText = '';
    });

    guess.addEventListener('click', (e) => {
        fetch('/guess-letter', { method: 'POST', credentials: 'include', body: letter.value }).then(function (response) {
            response.json().then(updateWordText);
        });
    });

    function updateWordText(data) {
        word.innerText = data.currentWord;
        remaining.innerText = data.remainingGuesses;

        if (data.remainingGuesses === 0) {
            status.innerText = 'Game Over. The dude is hanged.';
            guess.disabled = true;
        } else if (data.currentWord.indexOf("_") === -1) {
            status.innerText = 'YEAH YOU TOTALLY WON YES';
            guess.disabled = true;
        }
    }
};