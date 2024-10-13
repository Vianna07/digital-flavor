
# https://nixos.org/download
{ pkgs ? import <nixpkgs> {} }:

let
  commonPackages = with pkgs; [
    nodejs_20
    jdk21_headless
    docker
    maven
  ];
in

# nix-shell
if pkgs.lib.inNixShell then
  pkgs.mkShell {
    packages = commonPackages;
  }
# nix-env -i -f ./default.nix
else
  pkgs.buildEnv {
    name = "dev_environment";
    paths = commonPackages;
  }
