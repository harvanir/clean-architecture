package anothertest

import (
	bbsolute2 "github.com/harvanir/clean-architecture/go/akg/absolute"
	"github.com/harvanir/clean-architecture/go/pkg/absolute"
	"github.com/harvanir/clean-architecture/go/pkg/sometest"
	"log"
)

func Another() {
	absolute.Absolute()
	bbsolute2.Absolute()
	log.Printf("Another...")
}

func init() {
	log.Printf("init another...")
}

func PassingTheContext(ctx *sometest.TheContext) {
	ctx.Var = "change from passingTheContext"
}