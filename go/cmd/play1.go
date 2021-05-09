package main

import (
	"github.com/harvanir/clean-architecture/go/pkg/anothertest"
	"github.com/harvanir/clean-architecture/go/pkg/sometest"
	"log"
)

func play1() {
	context := sometest.NewTheContext()
	cvar := context.Var
	log.Printf("var: %v", cvar)
	anothertest.Another()
}
