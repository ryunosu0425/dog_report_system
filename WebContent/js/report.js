document.getElementById('add1').style.display = "none";

function func1() {
    let element = document.getElementById('image1');
    console.log(element.value);
    var add1 = document.getElementById('add1');

    if(element.value != null) {
        add1.style.display = "block";
    } else {
        add1.style.display = "none";
    }
  }


