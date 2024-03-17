let i = 0
function start() {
    setInterval(() => {api(i);i+=2;}, 1000 / 15)
    document.getElementById('but').style.display = 'none'
    const audio = document.getElementById('audio')
    audio.volume = 0.5
    audio.play()
}
function api(number) {
    var xhr = new XMLHttpRequest()
    xhr.open('GET',`/api/frame/${number}`, true)
    xhr.onreadystatechange = function() {
      if (xhr.readyState === XMLHttpRequest.DONE) {
        if (xhr.status === 200) {
            const responseText = xhr.responseText
            console.log(responseText)
            let formattedResponse = responseText.replace(/\n/g, '<br>')
            formattedResponse = formattedResponse.replace(/ /g, '&nbsp;')
            document.getElementById('response').innerHTML = formattedResponse
        } else {
            console.error('요청 실패:', xhr.status)
        }
      }
    };
    xhr.send();
}