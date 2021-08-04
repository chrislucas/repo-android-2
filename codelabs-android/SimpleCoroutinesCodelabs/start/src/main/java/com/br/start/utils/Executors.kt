package com.br.start.utils

import java.util.concurrent.Executors



// Um executor service tha can run Runnables off the main Thread :)
val executorService = Executors.newFixedThreadPool(2)