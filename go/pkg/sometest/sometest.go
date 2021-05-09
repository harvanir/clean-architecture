package sometest

import (
	absolute2 "github.com/harvanir/clean-architecture/go/akg/absolute"
	"github.com/harvanir/clean-architecture/go/pkg/absolute"
	"log"
)

type TheContext struct {
	Var string
}

type TheContext2 struct {
	Var string
}

func init() {
	log.Printf("init sometest...")
}

func NewTheContext() *TheContext {
	log.Printf("New...")
	context := TheContext{Var: "new Var"}
	log.Printf("memory NewTheContext: %p", &context)
	return &context
}

func test() {
	absolute.Absolute()
	log.Printf("test...")
	absolute2.Absolute()
}

func (c TheContext) Test() {
	//absolute.Absolute()
	//log.Printf("test...")
	test()
}

func (c TheContext) Print() {
	log.Printf("Var internal: %s", c.Var)
}

func (c TheContext) ChangeInternally(val string) {
	c.Var = val
	c.Print()
}

func PassingTheContext(ctx TheContext) {
	ctx.Var = "change from passingTheContext"
}

func NewTheContext2() TheContext2 {
	log.Printf("New...")
	return TheContext2{Var: "new Var"}
}

func (c TheContext2) Test() {
	//absolute.Absolute()
	//log.Printf("test...")
	test()
}

func (c TheContext2) Test2() {
	test()
}
