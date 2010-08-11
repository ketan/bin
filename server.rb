#!/usr/bin/env ruby

require 'webrick'

s = WEBrick::HTTPServer.new(
    :Port => 4000,
    :DocumentRoot => Dir.pwd
)
trap("INT") { s.shutdown }
s.start
