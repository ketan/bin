$LOAD_PATH.unshift File.expand_path(File.dirname(__FILE__))

use Rack::ShowExceptions

require 'git_http'

project_root = File.expand_path(File.join(`git rev-parse --show-cdup`, '/..'))

puts "Serving repository at #{project_root}"
config = {
  :project_root => project_root,
  :git_path => '/opt/local/libexec/git-core/git',
  :upload_pack => true,
  :receive_pack => true,
}

run GitHttp::App.new(config)
