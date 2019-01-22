'use strict';

const USERNAME = process.env.USERNAME;

const request = require('request');

console.log(USERNAME);
console.log('v2');

function loop() {
  request('http://game-server/view', { json: true }, (err, res, body) => {
    processData(body, function() {
      setTimeout(loop, 100);
    });
  });
}

function processData(data, callback) {
  let coinPosition = null;
  let myPosition = null;

  console.log(data);

  for (let entity of data.entities) {
    switch (entity.type) {
      case 'coin':
        coinPosition = entity.position;
        break;
      case 'player':
        if (entity.username === USERNAME) {
          myPosition = entity.position;
        }
        break;
    }
  }

  if (coinPosition === null || myPosition === null) {
    return callback();
  }

  let direction = null;
  if (coinPosition.column > myPosition.column) {
    direction = 'right';
  } else if (coinPosition.row > myPosition.row) {
    direction = 'down';
  } else if (coinPosition.column < myPosition.column) {
    direction = 'left';
  } else {
    direction = 'up';
  }
  console.log(direction);

  request('http://game-server/move/' + USERNAME + '/' + direction, {}, (err, res, body) => {
    callback();
  });
}

request('http://game-server/join/' + USERNAME, {}, (err, res, body) => {
  loop();
});