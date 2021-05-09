package main

import (
	"github.com/harvanir/clean-architecture/go/pkg/anothertest"
	"github.com/harvanir/clean-architecture/go/pkg/sometest"
	"log"
)

// inisialisasi object
func play2() {
	theContext := sometest.NewTheContext()
	log.Printf("memory NewTheContext client: %p", theContext)
	theContext.Test()
	//theContext.Print()
	theContext.Var = "from client"
	theContext.Print()
	log.Printf("Var client: %s", theContext.Var)
	log.Printf("change internally....")
	theContext.ChangeInternally("value change internally")
	log.Printf("print after change internally....")
	theContext.Print()
	sometest.PassingTheContext(*theContext)
	theContext.Print()
	anothertest.PassingTheContext(theContext)

	//theContext2 := sometest.NewTheContext2()
	//theContext2.Test2()
}
