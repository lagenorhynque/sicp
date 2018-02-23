#|
  This file is a part of sicp project.
  Copyright (c) 2018 Kent OHASHI (ignorantia.juris.non.excusa@gmail.com)
|#

(defsystem "sicp-test"
  :defsystem-depends-on ("prove-asdf")
  :author "Kent OHASHI"
  :license "MIT"
  :depends-on ("sicp"
               "prove")
  :components ((:module "test"
                :components
                ((:test-file "sicp"))))
  :description "Test system for sicp"

  :perform (test-op (op c) (symbol-call :prove-asdf :run-test-system c)))
