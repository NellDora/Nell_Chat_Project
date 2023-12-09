    document.addEventListener("DOMContentLoaded", function () {
      const chatDisplay = document.getElementById("chat-display");
      const chatInput = document.getElementById("chat-input");
      const fileInput = document.getElementById("file-input");
      var ws;

      // WebSocket 초기화 함수
      function wsOpen() {
        ws = new WebSocket("ws://localhost:8080/projectChat");
        wsEvt();
      }

      function wsEvt() {
        ws.onopen = function (data) {
          console.log("WebSocket opened");
        };

        ws.onmessage = function (data) {
          var msg = data.data;
          if (msg != null && msg.trim() != '') {
            appendMessage("<p>" + msg + "</p>");
          }
        };

        document.getElementById("send-button").addEventListener("click", function () {
          sendMessage();
        });

        document.getElementById("file-label").addEventListener("click", function () {
          fileInput.click();
        });

        document.addEventListener("keypress", function (e) {
          if (e.keyCode === 13) {
            sendMessage();
          }
        });

        fileInput.addEventListener("change", function () {
          const fileName = fileInput.files[0].name;
          sendMessage(`[파일 업로드] ${fileName}`);
        });
      }

      function sendMessage(message) {
        if (message == null) {
          message = chatInput.value.trim();
        }

        if (message !== "") {
          ws.send(message);
          chatInput.value = "";
        }
      }

      function appendMessage(message) {
        const messageElement = document.createElement("div");
        messageElement.className = "message";
        messageElement.innerHTML = message;
        chatDisplay.appendChild(messageElement);

        // 스크롤을 항상 아래로 이동
        chatDisplay.scrollTop = chatDisplay.scrollHeight;
      }

      // WebSocket 초기화 함수 호출
      wsOpen();
    });