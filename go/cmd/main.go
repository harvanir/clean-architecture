package main

import (
	"log"
)

/**
Belajar Go dari Java software engineer
1. go packaging x class java, accessibility, init initialization
2. go package public method x static method class
3. struct x class
4. struct method x class method
5. Inisialisasi object, package discovery, variable declaration, variable assignment, pointer vs non-pointer

6. inheritance struct x class
7. interface
8. interface implementation, satisfy method, duck type
n. clean architecture
*/

func main() {
	run()
	//play1()
	play2()
}

func run() {
	log.Printf("Run")
}

func init() {
	log.Printf("init main...")
}
