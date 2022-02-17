 const darkButton = document.getElementById("darkButton");
    const whiteButton = document.getElementById("whiteButton");

    darkButton.addEventListener("click",function(){
        darkButton.classList.add("hidden");
        whiteButton.classList.remove("hidden");
    });
    whiteButton.addEventListener("click",function(){
        whiteButton.classList.add("hidden");
        darkButton.classList.remove("hidden");
    })
